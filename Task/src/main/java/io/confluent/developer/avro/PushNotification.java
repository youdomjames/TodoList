/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.confluent.developer.avro;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class PushNotification extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 2739508055448022966L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PushNotification\",\"namespace\":\"io.confluent.developer.avro\",\"fields\":[{\"name\":\"title\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"message\",\"type\":[\"null\",\"string\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PushNotification> ENCODER =
      new BinaryMessageEncoder<PushNotification>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PushNotification> DECODER =
      new BinaryMessageDecoder<PushNotification>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<PushNotification> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<PushNotification> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PushNotification>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this PushNotification to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a PushNotification from a ByteBuffer. */
  public static PushNotification fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence title;
  @Deprecated public java.lang.CharSequence message;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PushNotification() {}

  /**
   * All-args constructor.
   * @param title The new value for title
   * @param message The new value for message
   */
  public PushNotification(java.lang.CharSequence title, java.lang.CharSequence message) {
    this.title = title;
    this.message = message;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return title;
    case 1: return message;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: title = (java.lang.CharSequence)value$; break;
    case 1: message = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'title' field.
   * @return The value of the 'title' field.
   */
  public java.lang.CharSequence getTitle() {
    return title;
  }

  /**
   * Sets the value of the 'title' field.
   * @param value the value to set.
   */
  public void setTitle(java.lang.CharSequence value) {
    this.title = value;
  }

  /**
   * Gets the value of the 'message' field.
   * @return The value of the 'message' field.
   */
  public java.lang.CharSequence getMessage() {
    return message;
  }

  /**
   * Sets the value of the 'message' field.
   * @param value the value to set.
   */
  public void setMessage(java.lang.CharSequence value) {
    this.message = value;
  }

  /**
   * Creates a new PushNotification RecordBuilder.
   * @return A new PushNotification RecordBuilder
   */
  public static io.confluent.developer.avro.PushNotification.Builder newBuilder() {
    return new io.confluent.developer.avro.PushNotification.Builder();
  }

  /**
   * Creates a new PushNotification RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PushNotification RecordBuilder
   */
  public static io.confluent.developer.avro.PushNotification.Builder newBuilder(io.confluent.developer.avro.PushNotification.Builder other) {
    return new io.confluent.developer.avro.PushNotification.Builder(other);
  }

  /**
   * Creates a new PushNotification RecordBuilder by copying an existing PushNotification instance.
   * @param other The existing instance to copy.
   * @return A new PushNotification RecordBuilder
   */
  public static io.confluent.developer.avro.PushNotification.Builder newBuilder(io.confluent.developer.avro.PushNotification other) {
    return new io.confluent.developer.avro.PushNotification.Builder(other);
  }

  /**
   * RecordBuilder for PushNotification instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PushNotification>
    implements org.apache.avro.data.RecordBuilder<PushNotification> {

    private java.lang.CharSequence title;
    private java.lang.CharSequence message;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.confluent.developer.avro.PushNotification.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.title)) {
        this.title = data().deepCopy(fields()[0].schema(), other.title);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.message)) {
        this.message = data().deepCopy(fields()[1].schema(), other.message);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing PushNotification instance
     * @param other The existing instance to copy.
     */
    private Builder(io.confluent.developer.avro.PushNotification other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.title)) {
        this.title = data().deepCopy(fields()[0].schema(), other.title);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.message)) {
        this.message = data().deepCopy(fields()[1].schema(), other.message);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'title' field.
      * @return The value.
      */
    public java.lang.CharSequence getTitle() {
      return title;
    }

    /**
      * Sets the value of the 'title' field.
      * @param value The value of 'title'.
      * @return This builder.
      */
    public io.confluent.developer.avro.PushNotification.Builder setTitle(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.title = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'title' field has been set.
      * @return True if the 'title' field has been set, false otherwise.
      */
    public boolean hasTitle() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'title' field.
      * @return This builder.
      */
    public io.confluent.developer.avro.PushNotification.Builder clearTitle() {
      title = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'message' field.
      * @return The value.
      */
    public java.lang.CharSequence getMessage() {
      return message;
    }

    /**
      * Sets the value of the 'message' field.
      * @param value The value of 'message'.
      * @return This builder.
      */
    public io.confluent.developer.avro.PushNotification.Builder setMessage(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.message = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'message' field has been set.
      * @return True if the 'message' field has been set, false otherwise.
      */
    public boolean hasMessage() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'message' field.
      * @return This builder.
      */
    public io.confluent.developer.avro.PushNotification.Builder clearMessage() {
      message = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PushNotification build() {
      try {
        PushNotification record = new PushNotification();
        record.title = fieldSetFlags()[0] ? this.title : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.message = fieldSetFlags()[1] ? this.message : (java.lang.CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PushNotification>
    WRITER$ = (org.apache.avro.io.DatumWriter<PushNotification>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PushNotification>
    READER$ = (org.apache.avro.io.DatumReader<PushNotification>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
