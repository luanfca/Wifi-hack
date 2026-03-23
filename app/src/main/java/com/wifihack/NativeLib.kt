package com.wifihack

class NativeLib {
    companion object {
        init {
            System.loadLibrary("wps")
            System.loadLibrary("hccl")
        }

        @JvmStatic
        external fun runWps(bssid: String): Boolean

        @JvmStatic
        external fun runHash(pcap: ByteArray, size: Int): Int
    }
}
