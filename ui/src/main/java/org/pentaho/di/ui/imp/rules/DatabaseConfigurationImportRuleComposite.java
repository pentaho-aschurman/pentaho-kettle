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


package org.pentaho.di.ui.imp.rules;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.imp.rule.ImportRuleInterface;
import org.pentaho.di.imp.rules.DatabaseConfigurationImportRule;
import org.pentaho.di.ui.core.PropsUI;
import org.pentaho.di.ui.core.database.dialog.DatabaseDialog;
import org.pentaho.di.ui.imp.rule.ImportRuleCompositeInterface;

public class DatabaseConfigurationImportRuleComposite implements ImportRuleCompositeInterface {

  private Button button;
  private Composite composite;
  private DatabaseConfigurationImportRule rule;
  private Label label;
  private DatabaseMeta databaseMeta;

  public Composite getComposite( Composite parent, ImportRuleInterface importRule ) {
    rule = (DatabaseConfigurationImportRule) importRule;
    databaseMeta = rule.getDatabaseMeta();
    PropsUI props = PropsUI.getInstance();

    composite = new Composite( parent, SWT.NONE );
    props.setLook( composite );
    composite.setLayout( new FillLayout() );

    label = new Label( composite, SWT.SINGLE | SWT.BORDER | SWT.LEFT );
    props.setLook( label );
    label.setText( "Database configuration : (not configured)" );

    button = new Button( composite, SWT.PUSH );
    button.setText( "Edit..." );
    button.addSelectionListener( new SelectionAdapter() {
      public void widgetSelected( SelectionEvent event ) {
        editDatabase();
      }
    } );

    return composite;
  }

  public void setCompositeData( ImportRuleInterface importRule ) {
    if ( databaseMeta != null ) {
      label.setText( "Database configuration: " + databaseMeta.getName() );
    }
  }

  public void getCompositeData( ImportRuleInterface importRule ) {
    rule.setDatabaseMeta( databaseMeta );
  }

  protected void editDatabase() {
    DatabaseMeta editMeta;
    if ( databaseMeta == null ) {
      editMeta = new DatabaseMeta();
    } else {
      editMeta = (DatabaseMeta) databaseMeta.clone();
    }
    DatabaseDialog databaseDialog = new DatabaseDialog( composite.getShell(), editMeta );
    if ( databaseDialog.open() != null ) {
      databaseMeta = editMeta;
      setCompositeData( rule );
    }
  }

}
