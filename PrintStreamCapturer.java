package main;

import java.io.PrintStream;

import javax.swing.JTextArea;

/**
 * 
 * @author ADASE
 *
 */
public class PrintStreamCapturer extends PrintStream {

    private JTextArea text;
    private boolean atLineStart;
    private String preLabel;
    private boolean debug;

    public PrintStreamCapturer(JTextArea textArea, PrintStream capturedStream, String preLabel) {
        super(capturedStream);
        this.text = textArea;
        this.preLabel = preLabel;
        this.atLineStart = true;
        this.debug = false;
    }

    public PrintStreamCapturer(JTextArea textArea, PrintStream capturedStream) {
    	super(capturedStream);
    	this.text = textArea;
    	this.preLabel = "";
    	this.atLineStart = true;
    	this.debug = false;
    }
    
    public PrintStreamCapturer(JTextArea textArea, PrintStream capturedStream, String preLabel, boolean debug) {
        super(capturedStream);
        this.text = textArea;
        this.preLabel = preLabel;
        this.atLineStart = true;
        this.debug = debug;
    }

    public PrintStreamCapturer(JTextArea textArea, PrintStream capturedStream, boolean debug) {
    	super(capturedStream);
    	this.text = textArea;
    	this.preLabel = "";
    	this.atLineStart = true;
    	this.debug = debug;
    }

    private void writeToTextArea(String str) {
        if (text != null) {
        	// Sort of like Mutual Exclusion - this block is similar to a critical section
            synchronized (text) {
                text.setCaretPosition(text.getDocument().getLength());
                text.append(str);
            }
        }
    }

    private void write(String str) {
        String[] s = str.split("\n", -1);
        if (s.length == 0)
            return;
        for (int i = 0; i < s.length - 1; i++) {
            writeWithPotentialPreLabel(s[i]);
            writeWithPotentialPreLabel("\n");
            atLineStart = true;
        }
        String last = s[s.length - 1];
        if (!last.equals("")) {
            writeWithPotentialPreLabel(last);
        }
    }

    private void writeWithPotentialPreLabel(String s) {
        if (atLineStart) {
            writeToTextArea(preLabel + s);
            atLineStart = false;
        } else {
            writeToTextArea(s);
        }
    }

    private void newLine() {
        write("\n");
    }

    @Override
    public void print(boolean b) {
        synchronized (this) {
        	if(debug == true) {
        		super.print(b);
        	}
            write(String.valueOf(b));
        }
    }

    @Override
    public void print(char c) {
        synchronized (this) {
        	if(debug == true) {
        		super.print(c);
        	}
            write(String.valueOf(c));
        }
    }

    @Override
    public void print(char[] s) {
        synchronized (this) {
        	if(debug == true) {
        		super.print(s);
        	}
            write(String.valueOf(s));
        }
    }

    @Override
    public void print(double d) {
        synchronized (this) {
        	if(debug == true) {
        		super.print(d);
        	}
            write(String.valueOf(d));
        }
    }

    @Override
    public void print(float f) {
        synchronized (this) {
        	if(debug == true) {
        		super.print(f);
        	}
            write(String.valueOf(f));
        }
    }

    @Override
    public void print(int i) {
        synchronized (this) {
        	if(debug == true) {
        		super.print(i);
        	}
            write(String.valueOf(i));
        }
    }

    @Override
    public void print(long l) {
        synchronized (this) {
        	if(debug == true) {
        		super.print(l);
        	}
            write(String.valueOf(l));
        }
    }

    @Override
    public void print(Object o) {
        synchronized (this) {
        	if(debug == true) {
        		super.print(o);
        	}
            write(String.valueOf(o));
        }
    }

    @Override
    public void print(String s) {
        synchronized (this) {
        	if(debug == true) {
        		super.print(s);
        	}
            if (s == null) {
                write("null");
            } else {
                write(s);
            }
        }
    }

    @Override
    public void println() {
        synchronized (this) {
            newLine();
            if(debug == true) {
            	super.println();
            }
        }
    }

    @Override
    public void println(boolean x) {
        synchronized (this) {
            print(x);
            newLine();
            if(debug == true) {
            	super.println();
            }
        }
    }

    @Override
    public void println(char x) {
        synchronized (this) {
            print(x);
            newLine();
            if(debug == true) {
            	super.println();
            }
        }
    }

    @Override
    public void println(int x) {
        synchronized (this) {
            print(x);
            newLine();
            if(debug == true) {
            	super.println();
            }
        }
    }

    @Override
    public void println(long x) {
        synchronized (this) {
            print(x);
            newLine();
            if(debug == true) {
            	super.println();
            }
        }
    }

    @Override
    public void println(float x) {
        synchronized (this) {
            print(x);
            newLine();
            if(debug == true) {
            	super.println();
            }
        }
    }

    @Override
    public void println(double x) {
        synchronized (this) {
            print(x);
            newLine();
            if(debug == true) {
            	super.println();
            }
        }
    }

    @Override
    public void println(char x[]) {
        synchronized (this) {
            print(x);
            newLine();
            if(debug == true) {
            	super.println();
            }
        }
    }

    @Override
    public void println(String x) {
        synchronized (this) {
            print(x);
            newLine();
            if(debug == true) {
            	super.println();
            }
        }
    }

    @Override
    public void println(Object x) {
        String s = String.valueOf(x);
        synchronized (this) {
            print(s);
            newLine();
            if (debug == true) {
            	super.println();
            }
            
        }
    }
}