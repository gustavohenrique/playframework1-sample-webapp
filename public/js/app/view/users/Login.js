Ext.define('PoupaNiquel.view.users.Login', {
	extend: 'Ext.window.Window',
    alias: 'widget.loginPanel',

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
        dockedItems: [{
        	xtype: 'toolbar',
	        dock: 'bottom',
	        id:'buttons',
	        ui: 'footer',
	        items: ['->', {
	            text: 'Ok',
	            action: 'authenticate',
	            width: 100,
	        }]
           
    	}]
    }],
    
    initComponent: function() {
        return this.callParent(arguments);
    }
});