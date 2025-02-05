/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2029-07-20
 ******************************************************************************/


package org.pentaho.di.core.exception;

import org.pentaho.di.core.Const;
import org.pentaho.di.repository.RepositoryObjectType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yury Bakhmutski
 * @since 9-25-2015
 *
 */
public class LookupReferencesException extends KettleException {
  public static final long serialVersionUID = 3337874569693837831L;

  /**
   * Pairs of path(filename) of object(s) which is(are) not found
   * and type of object(s)
   *
   * @see org.pentaho.di.repository.RepositoryObjectType
   */
  private Map<String, RepositoryObjectType> objectTypePairs = new HashMap<>();

  /**
   * Constructs a new throwable with null as its detail message.
   *
   * @param objectTypePairs - String-RepositoryObjectType pairs where path
   *                        is fully qualified path to object
   */
  public LookupReferencesException( Map<String, RepositoryObjectType> objectTypePairs ) {
    super();
    this.objectTypePairs = objectTypePairs;
  }

  /**
   * Constructs a new throwable with the specified detail message.
   *
   * @param message         - the detail message. The detail message is saved for later retrieval by the getMessage() method.
   * @param objectTypePairs - String-RepositoryObjectType pairs where path
   *                        is fully qualified path to object
   */
  public LookupReferencesException( String message, Map<String, RepositoryObjectType> objectTypePairs ) {
    super( message );
    this.objectTypePairs = objectTypePairs;
  }

  /**
   * Constructs a new throwable with the specified cause and a detail message of (cause==null ? null : cause.toString())
   * (which typically contains the class and detail message of cause).
   *
   * @param cause           - the cause (which is saved for later retrieval by the getCause() method). (A null value is permitted, and
   *                        indicates that the cause is nonexistent or unknown.)
   * @param objectTypePairs - String-RepositoryObjectType pairs where path
   *                        is fully qualified path to object
   */
  public LookupReferencesException( Throwable cause, Map<String, RepositoryObjectType> objectTypePairs ) {
    super( cause );
    this.objectTypePairs = objectTypePairs;
  }

  /**
   * Constructs a new throwable with the specified detail message and cause.
   *
   * @param message         - the detail message (which is saved for later retrieval by the getMessage() method).
   * @param cause           - the cause (which is saved for later retrieval by the getCause() method). (A null value is permitted, and
   *                        indicates that the cause is nonexistent or unknown.)
   * @param objectTypePairs - String-RepositoryObjectType pairs where path
   *                        is fully qualified path to object
   */
  public LookupReferencesException( String message, Throwable cause,
      Map<String, RepositoryObjectType> objectTypePairs ) {
    super( message, cause );
    this.objectTypePairs = objectTypePairs;
  }

  public String objectTypePairsToString() {
    StringBuilder result = new StringBuilder();
    for ( Map.Entry entry : objectTypePairs.entrySet() ) {
      if ( entry.getKey() != null ) {
        result.append( Const.CR );
        result.append( "\"" );
        result.append( entry.getKey() );
        result.append( "\"" );
        result.append( " [" );
        result.append( entry.getValue() );
        result.append( "] " );
      }
    }
    return result.toString();
  }

}
