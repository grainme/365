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
        writer.println("");
        writer.println("abstract class " + baseName + "{");
        writer.println("");

        // TODO: generate subclasses (types)

        writer.println("}");
        writer.close();
    }
}
