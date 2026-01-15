package com.grainme.jlox;

import com.grainme.jlox.Expr.*;

/**
 * the string representation we produce isn’t going to be Lox syntax.
 * Instead, it will look a lot like, well, Lisp.
 * Each expression is explicitly parenthesized,
 * and all of its subexpressions and tokens are contained in that.
 * Given a syntax tree like:
 *      *
 *   -     ()
 *  123    45,67
 *
 * (* (- 123) (group 45.67))
 *
 * */
public class AstPrinter implements Visitor<String> {

    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Binary expr) {
        return parenthesize(expr.op.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitUnaryExpr(Unary expr) {
        return parenthesize(expr.op.lexeme, expr.right);
    }

    @Override
    public String visitGroupingExpr(Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    private String parenthesize(String name, Expr... exprs) {
        return "";
    }
}
