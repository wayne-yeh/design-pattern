#!/bin/bash

# 執行 RPG 遊戲
if [ ! -f "rpg/Main.class" ]; then
    echo "程式尚未編譯，正在編譯..."
    ./compile.sh
    echo ""
fi

echo "啟動 RPG 遊戲..."
echo "請輸入遊戲數據或使用 Ctrl+D 結束輸入："
java -cp . rpg.Main
