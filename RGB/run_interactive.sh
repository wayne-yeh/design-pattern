#!/bin/bash

# 執行 RPG 即時對戰遊戲
if [ ! -f "rpg/RealTimeMain.class" ]; then
    echo "程式尚未編譯，正在編譯..."
    ./compile.sh
    echo ""
fi

echo "🎮 啟動 RPG 即時對戰模式！"
echo "💡 在這個模式中，您可以即時輸入決策"
echo ""
java -cp . rpg.RealTimeMain
