import java.util.HashMap;

/**
 * @Version 1.0
 * @Author:LiuXinYu
 * @Date:2020/6/1
 * @Content:
 */

class HashBuck{
    static class Node{
        public int key;
        public int val;
        public Node next;
        public Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    }
    public Node[] array = new Node[10];
    public int usedSize;

    public void put(int key,int val){
        //1.通过设计的哈希函数 找到对应的位置
        int index = key % array.length;
        for(Node cur = array[index];cur != null;cur = cur.next){
            if(cur.key == key){
                //key相同 更新val
                cur.val = val;
                return;
            }
        }
        Node node = new Node(key,val);
        node.next = array[index];
        array[index] = node;
        this.usedSize++;
        if(loadFactor() > 0.75){
            resize();
        }
    }
    public void resize(){
        //1.扩容需要进行重新哈希
        //2.头插法形式插入到newArray[index]
        Node[] newArray = new Node[2*this.array.length];
        for (int i = 0; i < array.length; i++) {
            Node cur = array[i];
            while (cur != null){
                Node curNext = cur.next;
                int index = cur.key % newArray.length;
                cur.next = newArray[i];
                newArray[index] = cur;
                cur = curNext;
            }
        }
        array = newArray;
    }

    public double loadFactor(){//负载因子
        return this.usedSize*1.0 / this.array.length;
    }

    public int get(int key){
        int index = key % array.length;
        Node cur = array[index];
        while(cur != null){
            if(cur.key == key){
                return cur.val;
            }
            cur = cur.next;
        }
        return -1;
    }

}
public class TestDemo {
    public static void main(String[] args) {
        HashBuck hashBuck = new HashBuck();
        for (int i = 0; i < 8; i++) {
            hashBuck.put(i,i);
        }
        hashBuck.put(11,101);
        System.out.println(hashBuck.get(11));
    }
}
