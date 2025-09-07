#!/bin/bash

# 編譯 RPG 專案
echo "編譯 RPG 專案..."
javac -cp . -d . src/main/java/rpg/**/*.java src/main/java/rpg/*.java

if [ $? -eq 0 ]; then
    echo "編譯成功！"
    echo ""
    echo "使用方式："
    echo "  1. 原版 (預設輸入)：    java -cp . rpg.Main"
    echo "  2. 測試模式：          java -cp . rpg.Main < test_input.txt"
    echo "  3. 互動引導模式：      java -cp . rpg.InteractiveMain"
    echo "  4. 即時對戰模式：      java -cp . rpg.RealTimeMain"
    echo ""
    echo "或使用執行腳本："
    echo "  ./run.sh              - 原版模式"
    echo "  ./run_interactive.sh  - 即時對戰模式"
else
    echo "編譯失敗！"
    exit 1
fi
