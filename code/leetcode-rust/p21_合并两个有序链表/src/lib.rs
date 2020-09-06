#[cfg(test)]
mod tests {
    #[test]
    fn it_works() {
        assert_eq!(2 + 2, 4);
    }
}


// Definition for singly-linked list.
#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
  pub val: i32,
  pub next: Option<Box<ListNode>>
}

impl ListNode {
  #[inline]
  fn new(val: i32) -> Self {
    ListNode {
      next: None,
      val
    }
  }
}

impl Solution {

    // pub fn merge_two_lists(
    //     l1: Option<Box<ListNode>>,
    //     l2: Option<Box<ListNode>>,
    // ) -> Option<Box<ListNode>> {
    //     let (mut lhs, mut rhs) = (l1, l2);
    //     let mut head = Box::new(ListNode::new(0));
    //     let mut tail = &mut head;
    //
    //     while let (Some(lnode), Some(rnode)) = (lhs.as_ref(), rhs.as_ref()) {
    //         if lnode.val <= rnode.val {
    //             tail.next = lhs;
    //             tail = tail.next.as_mut().unwrap();
    //             lhs = tail.next.take();
    //         } else {
    //             tail.next = rhs;
    //             tail = tail.next.as_mut().unwrap();
    //             rhs = tail.next.take();
    //         }
    //     }
    //
    //     tail.next = if lhs.is_some() { lhs } else { rhs };
    //     head.next
    // }
    pub fn merge_two_lists(l1: Option<Box<ListNode>>, l2: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        // 复制头指针为mut类型
        let (mut list1, mut list2) = (l1, l2);
        // 初始化新链表的头尾节点
        let mut head = Box::new(ListNode::new(0));
        let mut tail = &mut head;

        // 展开两链条的头结点
        while let (Some(head1), Some(head2)) = (list1.as_ref(), list2.as_ref()) {
            if head1.val <= head2.val {
                // 延展尾节点
                tail.next = list1;
                // 移动尾节点，注意需要获取为mut类型
                tail = tail.next.as_mut().unwrap();
                // 更新链1的头节点
                list1 = tail.next.take();
            } else {
                tail.next = list2;
                tail = tail.next.as_mut().unwrap();
                list2 = tail.next.take();
            }
        }

        // 处理剩余链表
        if list1.is_some() {
            tail.next = list1;
        } else {
            tail.next = list2;
        }

        // 头节点去掉初始化节点
        head.next
    }
}