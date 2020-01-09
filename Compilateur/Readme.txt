arm-none-eabi: https://developer.arm.com/tools-and-software/open-source-software/developer-tools/gnu-toolchain/gnu-rm/downloads
Pour compiler un programme c, utiliser la commande suivante: 
 ./arm-none-eabi-gcc -S -mthumb -mcpu=cortex-m0 -O0  -mfloat-abi=soft programme_a_compiler.c
