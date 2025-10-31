//java command injectoin

private String executeSystemCommand_Unsafe(HttpServletRequest request) 
		throws ServletException, IOException {
	String commandResult = ""; 
	
	String userCommand = request.getParameter("Command");
  
	try {
		Runtime runtime = Runtime.getRuntime();
		Process subProc = runtime.exec("/bin/sh -c \"" + PROGRAM_NAME + " " + userCommand+"\"");
		
		BufferedReader irProcOutput = new BufferedReader(new InputStreamReader(subProc.getInputStream()));

		String line = null;
        while ((line = irProcOutput.readLine()) != null)
			commandResult += line; 

		irProcOutput.close();		
	} catch (Exception ex) {
		handleExceptions(ex);
	}
	
	return commandResult;
}
