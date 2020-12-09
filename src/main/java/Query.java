  public void executeQuery () throws Exception{
		String jobId = getUserInput();
		System.out.println("\nThe query will find all employees who worked on the job with the id: " + jobId + "\n");

		try {
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT firstName, lastName FROM Staff AS s WHERE s.employeeId IN (SELECT c.employeeId FROM Carried_Out AS c WHERE c.jobId = '"+jobId+"')");
			ResultSet result = pstmt.executeQuery();

			int i = 0;
			System.out.println("LAST NAME\tFIRST NAME");
			while (result.next()) {
				System.out.print(result.getString("lastName") +"\t");
				System.out.println(result.getString("firstName"));
				i++;
			}
			System.out.println("(number of rows: "+ i+ ")");
			pstmt.close();
		}
		catch (Exception e) {
			System.err.println("System Exception in listEmp");
			e.printStackTrace();
			throw e;
		}
	}
  
  public String getUserInput(){
		Scanner userInput = new Scanner(System.in);

		int value = 0;
		boolean valid = false;

		//loop while user input is not valid.
		while(!valid)

		{

			//prompt user for input.
			System.out.println("Please enter a job id (integer) to query for");
			//initialise error.
			boolean error = false;
			try {

				//try getting user input as a String.
				value = userInput.nextInt();
				valid = true;

			}
			//catch any exceptions, display error to user and set error to true.
			catch (InputMismatchException e) {
				System.err.println("Please enter a valid integer");

				//consume invalid token so that it is not picked up in the next iteration
				userInput.next();
			}
		}
		return Integer.toString(value);

	}
