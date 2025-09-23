// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("setupCustomizer");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("setupCustomizer")
//      }
//    }

#include <jni.h>
#include <string.h>
#include <stdbool.h>
#include <sys/wait.h>
#include <unistd.h>

// array for package list.
const char *browserAppPaths[100][20] = {
        "skippable",
        "/product/tsu/apps/chrome.apk",
        "/product/tsu/apps/firefox.apk",
        "/product/tsu/apps/vivaldi.apk",
        "/product/tsu/apps/ungoogled_chrome.apk"
};

int executeShellCommands(const char *command, char * const arguments[]) {
    switch(fork()) {
        case -1:
            return -1;
        break;
        case 0:
            execvp(command, arguments);
        break;
        default: {
            int status;
            wait(&status);
            return WIFEXITED(status) ? WEXITSTATUS(status) : 1;
        }
    }
    return -1;
}

JNIEXPORT jboolean JNICALL Java_tsukika_misc_hyein_setupCustomizer_FinalizeActivity_installThisBrowser(JNIEnv *env,jobject thiz, jint browser) {
    if(browser != 1 && browser != 2 && browser != 3 && browser != 4) return JNI_FALSE;
    return (executeShellCommands("su", (char *[]) {"su", "-c", "pm", "install",(char *) browserAppPaths[browser], NULL}) == 0) ? JNI_TRUE : JNI_FALSE;
}