# WordleWordFinder

 THIS ALGORITHM USES WORDS FROM BEFORE WORDLE WAS BOUGHT BY THE NEW YORK TIMES
 ANY CHANGES TO THE LIST OF POSSIBLE WORDS MAY AFFECT THE ACCURACY OF THIS ALGORITHM

An algorithm to find the best Worlde starting word depending on the day

How it works
- Uses a hashmap to find the most common letters in the list of possible Wordle starting words
- Assigns an accuracy score to each letter based on the frequency of its appearance
- Assigns an accuracy score to each word by adding the accuracy of every letter in the word
- Sorts every word depending on its accuracy and returns the word with the highest accuracy
