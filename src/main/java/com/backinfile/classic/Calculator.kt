package com.backinfile.classic

import com.backinfile.assertEqualTo
import org.junit.Test

class Calculator {
    fun calc(str: String): Int {
        class Calc {
            var index = 0
            fun exprSum(): Int {
                var left = exprMul()
                while (index < str.length) {
                    val ch = str[index]
                    if (ch == '+') {
                        index++
                        val right = exprMul()
                        left += right
                    } else if (ch == '-') {
                        index++
                        val right = exprMul()
                        left -= right
                    } else {
                        break
                    }
                }
                return left
            }

            fun exprMul(): Int {
                var left = exprValue()
                while (index < str.length) {
                    val ch = str[index]
                    if (ch == '*') {
                        index++
                        val right = exprValue()
                        left *= right
                    } else {
                        break
                    }
                }
                return left
            }

            fun exprValue(): Int {
                val ch = str[index]
                if (ch == '(') {
                    index++
                    return exprSum().also { index++ }
                }
                var num = 0
                while (index < str.length && Character.isDigit(str[index])) {
                    num = num * 10 + (str[index++] - '0')
                }
                return num
            }
        }
        return Calc().exprSum()
    }


    @Test
    fun test() {
        1 assertEqualTo calc("1")
        2 assertEqualTo calc("1+1")
        10 assertEqualTo calc("(1+1)*5")
        14 assertEqualTo calc("1+(1+1)*5+3")
        6 assertEqualTo calc("1+1*5")
    }
}