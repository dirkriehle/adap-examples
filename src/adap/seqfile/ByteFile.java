package adap.seqfile;

public class ByteFile implements SeqFile {

	protected byte[] data;
	protected int length;

	public boolean isEmpty() {
		return length == 0;
	}


}
