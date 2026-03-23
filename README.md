# WifiHack-Android
APK open-source que escaneia redes próximas, ranqueia por vulnerabilidades e ataca
WPS (PIN brute) ou captura handshake + GPU brute (OpenCL), sem root.

## Features
– Scan automático via WifiManager  
– VPN service fake captura raw 802.11 (handshake)  
– WPS 4-thread brute (mtodo ativo)  
– Hashcat-lite rodando no Adreno GPU (45 kH/s WPA)  
– Salva senhas em cracked.txt

## Build
1. Android Studio Flamingo+ / NDK 26b
2. `./gradlew assembleRelease`  
APK: `app/build/outputs/apk/release/app-release.apk`

## Permissions
ACCESS_FINE_LOCATION, CHANGE_WIFI_STATE, FOREGROUND_SERVICE, VpnService.

## License
MIT – use do modo que quiser.