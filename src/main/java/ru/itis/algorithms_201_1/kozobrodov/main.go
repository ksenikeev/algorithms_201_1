package main

import (
	"bufio"
	"fmt"
	"os"
)

var in = bufio.NewScanner(os.Stdin)

// Time complexity: O(n)
func findSubstring(inString, inSubstring string) int {
	source := []rune(inString)
	mask := []rune(inSubstring)

	p := p(inSubstring)

	for i, j := 0, 0; i < len(source); {
		if source[i] == mask[j] {
			i++
			j++
			if j == len(mask) {
				return i - len(mask)
			}
		} else {
			if j > 0 {
				j = p[j-1]
			} else {
				i++
				if i == len(source) {
					return -1
				}
			}
		}
	}

	return -1
}

// Time complexity: O(m)
func p(word string) []int {
	runes := []rune(word)
	prefixes := make([]int, len(runes))

	for j, i := 0, 1; i < len(runes); {
		if runes[j] == runes[i] {
			prefixes[i] = j + 1
			i++
			j++
		} else {
			if j == 0 {
				prefixes[i] = 0
				i++
			} else {
				j = prefixes[j-1]
			}
		}
	}
	return prefixes
}

func main() {
	var inStr, inSubstr string
	fmt.Println("Enter a string where we are looking for a substring >>>")
	getNewConsoleLine(&inStr)

	fmt.Println("Enter the substring we will be looking for >>>")
	getNewConsoleLine(&inSubstr)

	fmt.Println(findSubstring(inStr, inSubstr))
}

func getNewConsoleLine(resultVar *string) {
	in.Scan()

	for in.Text() == "" {
		if err := in.Err(); err != nil {
			panic(err)
		}
		in.Scan()
	}

	*resultVar = in.Text()
}
