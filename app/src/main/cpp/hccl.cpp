#include <jni.h>

extern "C"
JNIEXPORT jint JNICALL
Java_com_wifihack_NativeLib_runHash(JNIEnv*, jclass, jbyteArray, jint) {
    // Demo stub: returns a fixed value until a real native hash path exists.
    return 87654321;
}
