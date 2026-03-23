package com.wifihack

class NativeLib {
    external fun runWps(bssid: String)
    external fun runHash(pcap: ByteArray, size: Int): Int

    companion object {
        init {
            System.loadLibrary("wps")
            System.loadLibrary("hccl")
        }
    }
}