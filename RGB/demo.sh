#!/bin/bash

echo "🎮 RPG 遊戲演示腳本"
echo ""
echo "選擇模式："
echo "1. 使用測試數據快速演示"
echo "2. 手動輸入數據（有詳細提示）"
echo "3. 即時對戰模式"
echo ""
read -p "請選擇模式 (1-3): " choice

case $choice in
    1)
        echo ""
        echo "🚀 使用測試數據運行..."
        echo ""
        java -cp . rpg.Main < test_input.txt
        ;;
    2)
        echo ""
        echo "📝 進入手動輸入模式，會有詳細的輸入提示..."
        echo ""
        java -cp . rpg.Main
        ;;
    3)
        echo ""
        echo "⚔️  進入即時對戰模式..."
        echo ""
        java -cp . rpg.RealTimeMain
        ;;
    *)
        echo "❌ 無效選擇"
        exit 1
        ;;
esac
