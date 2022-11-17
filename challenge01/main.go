package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {

	file, err := os.Open("users.txt")
	if err != nil {
		fmt.Println(err.Error())
	}
	defer file.Close()
	scanner := bufio.NewScanner(file)

	templateKeys := []string{"usr", "eme", "psw", "age", "loc", "fll"}
	dataComplete := ""
	var dataUser []string
	contador := 0
	user := ""
	for scanner.Scan() {
		if scanner.Text() != "" {
			dataComplete = dataComplete + scanner.Text() + " "
		} else {
			dataComplete = strings.Trim(dataComplete, " ")
			dataUser = strings.Split(dataComplete, " ")
			fmt.Println(dataUser)
			for i := 0; i < len(dataUser); i++ {
				userDataKey := strings.Split(dataUser[i], ":")[0]
				for j := 0; j < len(templateKeys); j++ {
					if userDataKey == templateKeys[j] {
						templateKeys = RemoveIndex(templateKeys, j)
						if userDataKey == "usr" {
							user = strings.Split(dataUser[i], ":")[1]
						}
						break
					}
				}
			}
			if len(templateKeys) != 0 {
				fmt.Println("INCORRECTO")
			} else {
				fmt.Println("CORRECTO")
				contador += 1
			}
			templateKeys = []string{"usr", "eme", "psw", "age", "loc", "fll"}
			dataComplete = ""
			fmt.Println("")
		}
	}
	fmt.Println(strconv.Itoa(contador) + user)
}

func RemoveIndex(s []string, index int) []string {
	return append(s[:index], s[index+1:]...)
}
