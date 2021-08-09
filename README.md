# Parsing Numbers in Clojure

```clojure
(parse-int "123")
=> 123

(parse-int "123abc")
Execution error (ExceptionInfo) at numparser.core/ascii->digit (core.clj:8).
failure: not digit.

(parse-float "123.456")
=> 123.456

(parse-float "123.abc")
Execution error (ExceptionInfo) at numparser.core/ascii->digit (core.clj:8).
failure: not digit.

(parse-float "123")
=> 123.0
```
