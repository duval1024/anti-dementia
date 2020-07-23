package com.al.p20_Valid_Parentheses;

import org.junit.Test;

import java.util.Stack;

/**
 * @author duval
 * @date 2019-10-13 14:43
 */
public class Solution {

    private boolean isLeft(char c) {
        return c == '[' || c == '{' || c == '(';
    }

    private boolean isCouple(char a, char b) {
        return (a == '[' && b == ']')
                || (a == '(' && b == ')')
                || (a == '{' && b == '}');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char nextChar = s.charAt(i);
            if (isLeft(nextChar)) {
                stack.push(nextChar);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            } else {
                char top = stack.peek();
                if (isCouple(top, nextChar)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void test() {
        assert isValid("()[]{}") == true;
        assert isValid("(]") == false;
        assert isValid("{[]}") == true;
    }
}
