<%@page isErrorPage="true" import="java.io.*" contentType="text/plain"%>

Message:
${exception.printStackTrace(response.getWriter())}
