#include <jni.h>
#include <string.h>
#include <pthread.h>
#include <stdint.h>
#include <android/log.h>

#define LOG(msg)  __android_log_write(ANDROID_LOG_INFO,"wpsbrute",msg)

static int stop = 0;

int try_pin(const char* bssid, unsigned pin){
    // stub: aqui enviaría o pacote EAP-WSC real
    // retorna 1 se receber M7
    return (pin == 12345670); // demo
}

void* worker(void* arg){
    unsigned start = (unsigned)(uintptr_t)arg;
    unsigned end = start + 2500;
    unsigned char mac[6];
    for(unsigned p=start; p<end && !stop; p++){
        if(try_pin("00:11:22:33:44:55", p)){
            LOG("found pin!");
            stop = 1;
            break;
        }
    }
    return NULL;
}

JNIEXPORT jboolean JNICALL Java_com_wifihack_NativeLib_runWps(
    JNIEnv* env,
    jclass clazz,
    jstring bssid
){
    pthread_t t[4];
    stop = 0;
    for(int i=0;i<4;i++)
        pthread_create(&t[i],NULL,worker,(void*)(i*2500));
    for(int i=0;i<4;i++)
        pthread_join(t[i],NULL);
    return stop ? JNI_TRUE : JNI_FALSE;
}
