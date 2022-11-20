package main

import (
	"encoding/json"
	"fmt"
	"os"
)

func main() {

	file, err := os.Open("colors.txt")
	if err != nil {
		fmt.Print(err)
	}
	defer file.Close()

	var arr []string
	err = json.NewDecoder(file).Decode(&arr)
	if err != nil {
		panic(err)
	}

	contador := 1
	color := ""
	total := 0
	for i := 2; i < len(arr); i++ {
		if i%2 == 1 {
			if (arr[i] == arr[i-2]) && (arr[i+1] == arr[i-1]) {
				contador++
				color = arr[i+1]
			} else {
				if contador > total {
					total = contador
				}
				contador = 1
			}
		}
	}
	fmt.Printf("%d@%s\n", total*2, color)
}
