Ext.define('PoupaNiquel.view.users.Login', {
	extend: 'Ext.window.Window',
    alias: 'widget.login',

    title: 'Authentication',
    width: 300,
    height: 150,
    layout: 'fit',
    border: 0,
    closable: false,
//    resizable: false,
    plain: true,
    
    items: [{
    	xtype: 'form',
    	bodyPadding: 10,
    	
    	
    	fieldDefaults: {
            labelWidth: 60,
            anchor: '100%',
            allowBlank: false,
            msgTarget: 'side',
            selectOnFocus: true,
            enableKeyEvents: true,
        },
        
    	items: [{
    		fieldLabel: 'E-mail',
    		xtype: 'textfield',
    		name: 'username',
        }, {
        	fieldLabel: 'Password',
           	xtype: 'textfield',
           	name: 'password',
           	inputType: 'password',
        }],
        
    }],
    
    initComponent: function() {
    	this.buttons = [{
            text: 'Ok',
            action: 'authenticate',
            width: 100,
        }];
    	
        this.callParent(arguments);
    }
});