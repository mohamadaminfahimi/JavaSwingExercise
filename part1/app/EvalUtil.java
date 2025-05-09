package part1.app;


public class EvalUtil {
    // Evaluates the expression or returns "Error" on failure
    public static String eval(String expression) {
        // Local parser class inside the method
    class Parser {
        final String s = expression;
        int pos = -1, ch;

        void nextChar() {
            pos++;
            ch = (pos < s.length() ? s.charAt(pos) : -1);
        }

        boolean eat(int charToEat) {
            while (ch == ' ') nextChar();
            if (ch == charToEat) {
                nextChar();
                return true;
            }
            return false;
        }

        double parse() {
            nextChar();
            double x = parseExpression();
            if (pos < s.length()) 
                throw new RuntimeException("Unexpected: " + (char)ch);
            return x;
        }

        double parseExpression() {
            double x = parseTerm();
            for (;;) {
                if      (eat('+')) x += parseTerm();
                else if (eat('-')) x -= parseTerm();
                else return x;
            }
        }

        double parseTerm() {
            double x = parseFactor();
            for (;;) {
                if      (eat('*')) x *= parseFactor();
                else if (eat('/')) x /= parseFactor();
                else return x;
            }
        }

        double parseFactor() {
            if (eat('+')) return parseFactor();  // unary +
            if (eat('-')) return -parseFactor(); // unary –
            
            double x;
            int start = this.pos;
            if (eat('(')) {
                x = parseExpression();
                if (!eat(')')) throw new RuntimeException("Missing )");
            } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                x = Double.parseDouble(s.substring(start, this.pos));
            } else {
                throw new RuntimeException("Unexpected: " + (char)ch);
            }
            return x;
        }
    }

    try {
        double result = new Parser().parse();
        // strip trailing .0 for integers
        if (result == (long)result) return Long.toString((long)result);
        else return Double.toString(result);
    } catch (Exception e) {
        return "Error";
    }
    }
}
