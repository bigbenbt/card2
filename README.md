Running the project

This project requires Java 8 to run.  Once that is installed, run the following command from the project directory:

./gradlew clean build

This will compile the project and run all of the unit tests.  In order to run the program, navigate to the 
build/resources/main directory.  Execute the program in either of the following ways:

./cards.sh <path to input file>

or

./cards.sh < <path to input file>

The program will run and output the results to the terminal.  

Essay portion

Why did I pick Java 8?  Because it's the language I know best.  No other reason. 

Explain any design decisions I made

I chose to create two parser classes because they are operating on fundamentally different things.  I could have skipped
making them classes completely and just handled the logic within the main class to parse the two inputs, but this was
easier to test, and it made more sense from an OO standpoint.  I also made the ingest methods static, because these are
performing essentially stateless operations.  They don't need to carry anything around, so I felt comfortable just have
these methods be static utilities.  I also wanted them to scrub the data before it was passed in to the program, so 
everything else could just focus on logic and computation; that's why they both remove dollar signs.  

Operations probably doesn't have to be an enum, but I prefer that.  It's cleaner and gives us sanity checks on the inputs
for free (operations have to be ADD, CHARGE, or CREDIT, and we now have that check forever).

I decided to make cardholder it's own domain object with utility methods because we needed the ability to increase and 
decrease the balance of the cardholder at will, and that didn't make sense anywhere else.  I put the check on the card 
number validity there because that seemed like the logical place - we're requesting information about the cardholder, 
and whether the number is valid or not is part of that. 

The transaction processor stores everything in a TreeMap by cardholder name - this made it trivial to print everything 
out alphabetically at the end.  I thought about coming up with some kind of database solution, and if we were writing
this to run continuously (like as part of a live production service) I definitely would, but based on the description
of this as a "one and done" sort of program, I thought that a simple map like this made more sense.
