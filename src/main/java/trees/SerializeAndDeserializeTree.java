package trees;

import models.BinaryNode;

import java.util.Stack;

public class SerializeAndDeserializeTree {

    public static String serialize(final BinaryNode root) {
        final Stack<BinaryNode> stack = new Stack<>();
        final StringBuilder sb = new StringBuilder();

        // preorder
        stack.push(root);
        while(!stack.isEmpty()) {
            final BinaryNode current = stack.pop();

            sb.append(current.getId()).append(",");

            if(current.getRight() != null) {
                stack.push(current.getRight());
            }

            if(current.getLeft() != null) {
                stack.push(current.getLeft());
            }
        }

        sb.deleteCharAt(sb.length()).append("|");

        // inorder
        stack.push(root);
        BinaryNode current = root;
        while(!stack.isEmpty() || current != null) {
            if(current != null) {
                stack.push(current);
                current = current.getLeft();
            } else {
                final BinaryNode top = stack.pop();
                sb.append(top.getId()).append(",");
                current = top.getRight();
            }
        }

        return sb.deleteCharAt(sb.length()).toString();
    }

    public static BinaryNode deserialize(final String serialization) {
        throw new UnsupportedOperationException();
    }
}
