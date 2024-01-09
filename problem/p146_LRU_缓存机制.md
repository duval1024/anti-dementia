# p146_LRU_缓存机制
## 题目描述
运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。 

 
 实现 LRUCache 类： 
 - LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存 
 - int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
 - void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
 
 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？ 

 示例： 

```
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
```
```
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]
```
```
解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1);  缓存是 {1=1}
lRUCache.put(2, 2);  缓存是 {1=1, 2=2}
lRUCache.get(1);     返回 1
lRUCache.put(3, 3);  该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);     返回 -1 (未找到)
lRUCache.put(4, 4);  该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);     返回 -1 (未找到)
lRUCache.get(3);     返回 3
lRUCache.get(4);     返回 4
 ```

 

 提示： 

 ```
 1 <= capacity <= 3000 
 0 <= key <= 3000 
 0 <= value <= 104 
 最多调用 3 * 104 次 get 和 put 
 ```
 
## 题目难度
> ★★★★
## 核心知识
哈希表、双向链表
## 解题思路
如果用Java内部数据结构实现该功能，可以直接继承LinkedHashMap来实现。但这题目真正考查的时候肯定是希望通过哈希表+双向链表来手工实现O(1)复杂度的。

因此，在这个思路基础上我得到了第一版AC代码（略丑）：

```java
class LRUCache {

    class ListNode {
        int key;
        int val;
        ListNode pre;
        ListNode next;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private ListNode head;

    private ListNode tail;

    private Map<Integer, ListNode> hash;


    private int capacity;

    private void addToHead(ListNode node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            ListNode tmp = head;
            head = node;
            head.next = tmp;
            head.pre = null;
            tmp.pre = head;
        }

        hash.put(node.key, node);
    }

    private ListNode deleteNode(ListNode node) {
        if (node == null) {
            return null;
        }

        ListNode nextNode = node.next;
        ListNode preNode = node.pre;

        if (nextNode != null) {
            nextNode.pre = preNode;
        }

        if (preNode != null) {
            preNode.next = nextNode;
        }

        node.pre = null;
        node.next = null;

        if (node == tail && preNode != null) {
            tail = preNode;
        }

        if (node == head && nextNode != null) {
            head = nextNode;
        }

        hash.remove(node.key, node);
        return node;
    }


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
        this.hash = new HashMap<>(capacity);
    }

    public int get(int key) {
        if (hash.containsKey(key)) {
            ListNode current = hash.get(key);
            deleteNode(current);
            addToHead(current);
            return current.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (hash.containsKey(key)) {
            ListNode current = deleteNode(hash.get(key));
            current.val = value;
            addToHead(current);
        } else {
            ListNode current = new ListNode(key, value);
            addToHead(current);
            if (hash.size() > capacity) {
                deleteNode(tail);
            }
        }
    }
}

```

总体思路是没毛病的，有些代码上可以优化下。

使用go又做了一遍：

```go

type Node struct {
	key   int
	value int
	pre   *Node
	next  *Node
}

type LRUCache struct {
	head     *Node
	tail     *Node
	cache    map[int]*Node
	capacity int
	size     int
}

func initNode(key int, value int) *Node {
	return &Node{
		key:   key,
		value: value,
	}
}

func Constructor(capacity int) LRUCache {
	l := LRUCache{
		size:     0,
		capacity: capacity,
		head:     initNode(0, 0),
		tail:     initNode(0, 0),
		cache:    make(map[int]*Node),
	}
    // 技巧：头尾节点都用一个空节点占用着，方便处理
	l.head.next = l.tail
	l.tail.pre = l.head
	return l
}

func (this *LRUCache) moveToHead(node *Node) {
	// 将自己从原位置断开
	node.next.pre = node.pre
	node.pre.next = node.next
	// 将自己和head后的一个节点接起来
	node.next = this.head.next
	this.head.next.pre = node
	// 将自己与head接起来
	this.head.next = node
	node.pre = this.head
}

func (this *LRUCache) addToHead(node *Node) {
	// 将自己和head后的一个节点接起来
	node.next = this.head.next
	this.head.next.pre = node
	// 将自己与head接起来
	this.head.next = node
	node.pre = this.head
}

func (this *LRUCache) removeFromTail() *Node {
    // 记录待删除的节点
	deleteNode := this.tail.pre
	deleteNode.pre.next = this.tail
	this.tail.pre = deleteNode.pre
	return deleteNode
}

func (this *LRUCache) Get(key int) int {
	if node, ok := this.cache[key]; !ok {
		return -1
	} else {
		this.moveToHead(node)
		return node.value
	}
}

func (this *LRUCache) Put(key int, value int) {
	if node, ok := this.cache[key]; !ok {
		newNode := initNode(key, value)
		this.addToHead(newNode)
		this.cache[key] = newNode
		this.size++
		if this.size > this.capacity {
			removeNode := this.removeFromTail()
			delete(this.cache, removeNode.key)
			this.size--
		}
	} else {
        node.value = value
		this.moveToHead(node)
	}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */



```

 

## 其他补充

这道题本质上就是考察哈希表和链表的组合使用，算法上来说非常简单，主要考查代码编写能力。

我只用了几分钟就撸出第一版代码。

然而实际面试中，很多应聘者都没法手撕这道题，真是遗憾 = =。
