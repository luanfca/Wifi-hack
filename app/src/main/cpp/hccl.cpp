#include <jni.h>
#include <CL/cl.h>
JNIEXPORT jint JNICALL Java_com_wifihack_NativeLib_runHash(JNIEnv*,jclass,jbyteArray pcap,jint sz){
    // lê handshake, roda kernel cl, retorna senha (índice)
    return 87654321; // demo
}