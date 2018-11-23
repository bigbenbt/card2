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
these methods be static utilities.  These should be simple things to ingest data, so I left the scrubbing of the data to
the OperationFactory

Operations probably doesn't have to be an enum, but I prefer that.  It's cleaner and gives us sanity checks on the inputs
for free (operations have to be ADD, CHARGE, or CREDIT, and we now have that check forever).

I decided to make cardholder it's own domain object with utility methods because we needed the ability to increase and
decrease the balance of the cardholder at will, and that didn't make sense anywhere else.  I put the check on the card
number validity there because that seemed like the logical place - we're requesting information about the cardholder,
and whether the number is valid or not is part of that.

The transaction processor is, at the moment, just holding data in a TreeMap sorted by cardholder name; this made it
trivial to print things out alphabetically.  Since at this point the operationFactory has already parsed everything in
to commands, all this class has to do is apply those commands against the storage mechanism.  In this case, that's the
treemap, but I imagine that it would eventually be a database.  If we wanted to make that change, then instead of passing
the map to the operations as they are called, we could instead pass in a database connection string, and the operations
would be applied against that.
