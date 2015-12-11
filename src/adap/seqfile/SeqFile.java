package adap.seqfile;

public class SeqFile {
	
	public boolean isOpen() { return false; }
	public boolean isClosed() { return false; }
	public boolean isDeleted() { return false; }
	
	public SeqFile(String name) {
		// do nothing
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
