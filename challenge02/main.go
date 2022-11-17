package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {

	file, err := os.Open("encrypted.txt")
	if err != nil {
		fmt.Println(err.Error())
	}
	defer file.Close()
	scanner := bufio.NewScanner(file)
	scanner.Split(bufio.ScanWords)

	var numberList []string
	var mensaje string
	for scanner.Scan() {
		numberList = strings.Split(scanner.Text(), "")
		for i := 0; i < len(numberList); i++ {
			numero := numberList[i] + numberList[i+1]
			intVar, err := strconv.Atoi(numero)
			if err != nil {
				fmt.Println(err.Error())
			}
			if intVar <= 122 && intVar >= 97 {
				character := string(rune(intVar))
				mensaje += character
				i++
			} else {
				numero = numero + numberList[i+2]
				intVar, err = strconv.Atoi(numero)
				if intVar <= 122 && intVar >= 97 {
					character := string(rune(intVar))
					mensaje += character
					i++
					i++
				}
			}
		}
		mensaje += " "
	}
	fmt.Println(mensaje)
}
