package adap.seqfile;

public class  AbstractSeqFile implements SeqFile {
	
	protected AbstractSeqFile(String name) {
		// do nothing
	}
	
	public boolean isOpen() {
		// do sonething
		return false;
	}
	
	public boolean isClosed() { 
		// do sonething
		return false;
	}

	public boolean isDeleted() { 
		// do sonething
		return false;
	}
	
	public byte[] read() {
		assert isOpen();
		return new byte[0];
		// ...
	}
	
	public void write(byte[] data) {
		assert isOpen();
		// ...
	}
	
	public void delete() {
		assert isClosed();
		// ...
	}
	
}
