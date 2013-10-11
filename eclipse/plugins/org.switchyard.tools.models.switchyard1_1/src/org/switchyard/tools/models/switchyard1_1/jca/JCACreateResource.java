/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.switchyard.tools.models.switchyard1_1.jca;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>JCA Create Resource</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.switchyard.tools.models.switchyard1_1.jca.JcaPackage#getJCACreateResource()
 * @model extendedMetaData="name='JCACreateResource'"
 * @generated
 */
public enum JCACreateResource implements Enumerator {
    /**
     * The '<em><b>Always</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ALWAYS_VALUE
     * @generated
     * @ordered
     */
    ALWAYS(0, "always", "always"),

    /**
     * The '<em><b>Never</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NEVER_VALUE
     * @generated
     * @ordered
     */
    NEVER(1, "never", "never"),

    /**
     * The '<em><b>If Not Exist</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #IF_NOT_EXIST_VALUE
     * @generated
     * @ordered
     */
    IF_NOT_EXIST(2, "ifNotExist", "ifNotExist");

    /**
     * The '<em><b>Always</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Always</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ALWAYS
     * @model name="always"
     * @generated
     * @ordered
     */
    public static final int ALWAYS_VALUE = 0;

    /**
     * The '<em><b>Never</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Never</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NEVER
     * @model name="never"
     * @generated
     * @ordered
     */
    public static final int NEVER_VALUE = 1;

    /**
     * The '<em><b>If Not Exist</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>If Not Exist</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #IF_NOT_EXIST
     * @model name="ifNotExist"
     * @generated
     * @ordered
     */
    public static final int IF_NOT_EXIST_VALUE = 2;

    /**
     * An array of all the '<em><b>JCA Create Resource</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final JCACreateResource[] VALUES_ARRAY =
        new JCACreateResource[] {
            ALWAYS,
            NEVER,
            IF_NOT_EXIST,
        };

    /**
     * A public read-only list of all the '<em><b>JCA Create Resource</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<JCACreateResource> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>JCA Create Resource</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static JCACreateResource get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            JCACreateResource result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>JCA Create Resource</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static JCACreateResource getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            JCACreateResource result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>JCA Create Resource</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static JCACreateResource get(int value) {
        switch (value) {
            case ALWAYS_VALUE: return ALWAYS;
            case NEVER_VALUE: return NEVER;
            case IF_NOT_EXIST_VALUE: return IF_NOT_EXIST;
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
    private JCACreateResource(int value, String name, String literal) {
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
    
} //JCACreateResource
