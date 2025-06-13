## 二叉查找树(Binary Search Tree)，也称有序二叉树(ordered binary tree) 或 排序二叉树(sorted binary tree)
![img_1.png](imgs/22/img_1.png)
![img_2.png](imgs/22/img_2.png)

### 数据结构
![img_4.png](imgs/22/img_4.png)
#### 关于结点计数器 N
size(x) = size(x.left) + size(x.right) + 1
![img_5.png](imgs/22/img_5.png)
![img_3.png](imgs/22/img_3.png)

### get()方法
![img_6.png](imgs/22/img_6.png)
![img_7.png](imgs/22/img_7.png)

### put()方法
![img_9.png](imgs/22/img_9.png)
![img_8.png](imgs/22/img_8.png)

### 输入序列 S E A R C H E X A M P L E 构建二叉查找树的过程
![img_10.png](imgs/22/img_10.png)

### 如果输入序列是 A C E H R S X 会如何？

### 删除操作
仔细想想，这是个问题。

## 有序性相关的方法
### 最大键、最小键
![img_11.png](imgs/22/img_11.png)

### 向上取整和向下取整
![img_12.png](imgs/22/img_12.png)
#### 另一个版本的floor
![img_13.png](imgs/22/img_13.png)
![img_14.png](imgs/22/img_14.png)

![img_15.png](imgs/22/img_15.png)

### select() 和 排名rank()
![img_18.png](imgs/22/img_18.png)
![img_17.png](imgs/22/img_17.png)
rank()是select()的逆方法
![img_16.png](imgs/22/img_16.png)

### 删除操作
#### 删除最小键和删除最大键
![img_19.png](imgs/22/img_19.png)
![img_20.png](imgs/22/img_20.png)
![img_21.png](imgs/22/img_21.png)

#### 删除操作
![img_23.png](imgs/22/img_23.png)
![img_22.png](imgs/22/img_22.png)

### 范围查找 
![img_24.png](imgs/22/img_24.png)
![img_25.png](imgs/22/img_25.png)

### 性能总结
![img_26.png](imgs/22/img_26.png)


