package com.grainme.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class GenerateAST {

    public static void main(String... args) {
        if (args.length < 1) {
            System.err.println("Usage: generate_ast <output dir>");
            System.exit(64);
        }
        String outputDir = args[0];
        System.out.println("Output Dir: " + outputDir);

        try {
            defineAst(
                outputDir,
                "Expr",
                Arrays.asList(
                    "Binary     : Expr left, Token op, Expr right",
                    "Unary      : Token op, Expr right",
                    "Grouping   : Expr expression",
                    "Literal    : Object value"
                )
            );
        } catch (IOException e) {
            System.err.println("Failed to generate AST: " + e.getMessage());
        }
    }

    private static void defineAst(
        String outputDir,
        String baseName,
        List<String> types
    ) throws IOException {
        String path = outputDir + "/" + baseName + ".java";
        PrintWriter writer = new PrintWriter(path, Charset.defaultCharset());

        writer.println("package com.grainme.jlox;");
        writer.println();
        writer.println("abstract class " + baseName + "{");
        writer.println();

        defineVisitor(writer, baseName, types);

        for (String type : types) {
            String[] typeSplit = type.split(":");
            String className = typeSplit[0].trim();
            String fields = typeSplit[1].trim();
            defineType(writer, baseName, className, fields);
        }

        writer.println();
        writer.println("    abstract <R> R accept(Visitor<R> visitor);");

        writer.println("}");
        writer.close();
    }

    private static void defineVisitor(
        PrintWriter writer,
        String baseName,
        List<String> types
    ) {
        writer.println("    interface Visitor<R> {");

        // type be like: "Unary      : Token op, Expr right"$
        // this should produce:
        //      R visitUnaryExpr(Unary expr);
        for (String type : types) {
            String typeName = type.split(":")[0].trim();
            writer.println(
                "    R visit" +
                    typeName +
                    baseName +
                    "(" +
                    typeName +
                    " " +
                    baseName.toLowerCase() +
                    ");"
            );
        }

        writer.println("    }");
    }

    private static void defineType(
        PrintWriter writer,
        String baseName,
        String className,
        String fields
    ) {
        writer.println(
            "   static class " + className + " extends " + baseName + "{"
        );

        // fields
        String[] fieldList = fields.split(", ");
        writer.println();
        for (String field : fieldList) {
            String typeName = field.split(" ")[0].trim();
            String valueName = field.split(" ")[1].trim();
            writer.println("    final " + typeName + " " + valueName + ";");
        }

        // constructor
        writer.println("        " + className + "(" + fields + ") {");

        // Store params in fields
        for (String field : fieldList) {
            String valueName = field.split(" ")[1].trim();
            writer.println("      this." + valueName + " = " + valueName + ";");
        }

        writer.println("    }");

        // Visitor pattern
        writer.println();
        writer.println("    @Override");
        writer.println("    <R> R accept(Visitor<R> visitor) {");
        writer.println(
            "        return visitor.visit" + className + baseName + "(this);"
        );
        writer.println("    }");

        writer.println("  }");
    }
}
   