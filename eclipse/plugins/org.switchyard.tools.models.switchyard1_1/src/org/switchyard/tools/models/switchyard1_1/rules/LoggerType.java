/**
 */
package org.switchyard.tools.models.switchyard1_1.rules;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Logger Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.switchyard.tools.models.switchyard1_1.rules.RulesPackage#getLoggerType()
 * @model extendedMetaData="name='loggerType'"
 * @generated
 */
public enum LoggerType implements Enumerator {
    /**
     * The '<em><b>CONSOLE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CONSOLE_VALUE
     * @generated
     * @ordered
     */
    CONSOLE(0, "CONSOLE", "CONSOLE"),

    /**
     * The '<em><b>FILE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FILE_VALUE
     * @generated
     * @ordered
     */
    FILE(1, "FILE", "FILE"),

    /**
     * The '<em><b>THREADEDFILE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #THREADEDFILE_VALUE
     * @generated
     * @ordered
     */
    THREADEDFILE(2, "THREADEDFILE", "THREADED_FILE");

    /**
     * The '<em><b>CONSOLE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>CONSOLE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CONSOLE
     * @model
     * @generated
     * @ordered
     */
    public static final int CONSOLE_VALUE = 0;

    /**
     * The '<em><b>FILE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>FILE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FILE
     * @model
     * @generated
     * @ordered
     */
    public static final int FILE_VALUE = 1;

    /**
     * The '<em><b>THREADEDFILE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>THREADEDFILE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #THREADEDFILE
     * @model literal="THREADED_FILE"
     * @generated
     * @ordered
     */
    public static final int THREADEDFILE_VALUE = 2;

    /**
     * An array of all the '<em><b>Logger Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final LoggerType[] VALUES_ARRAY =
        new LoggerType[] {
            CONSOLE,
            FILE,
            THREADEDFILE,
        };

    /**
     * A public read-only list of all the '<em><b>Logger Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<LoggerType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Logger Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LoggerType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LoggerType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Logger Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LoggerType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LoggerType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Logger Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static LoggerType get(int value) {
        switch (value) {
            case CONSOLE_VALUE: return CONSOLE;
            case FILE_VALUE: return FILE;
            case THREADEDFILE_VALUE: return THREADEDFILE;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private LoggerType(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getValue() {
      return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
      return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLiteral() {
      return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }
    
} //LoggerType
