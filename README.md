# Awt-Animation

Awt用(主にSwing)のアニメーションツールです。今更いったい誰が使うんだろうと思いつつ必要だったので作りました。

# 使い方
## 基本
`Sample.java` を見れば分かります。

## 新しいアニメーションを作る
以下のように、`Animation` クラスを継承してください。その際、コンストラクタでは `super(length)` とする必要があります。
アニメーションの記述は `animate` で行います。第二引数の `rate` はアニメーションの完了度を表して言います。0がスタート、1が完了です。

```
public class MoveAnimation extends Animation{
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;

    public MoveAnimation(int fromX, int fromY, int toX, int toY, double length){
        super(length);
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    @Override
    public void animate(Component component, double rate){
        int posX = (int) ((1 - rate) * fromX + rate * toX);
        int posY = (int) ((1 - rate) * fromY + rate * toY);
        Rectangle r = component.getBounds();
        component.setBounds(posX, posY, r.width, r.height);
    }
}
```
