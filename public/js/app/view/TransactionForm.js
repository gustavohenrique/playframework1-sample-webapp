Ext.define('PoupaNiquel.view.TransactionForm', {
    extend: 'Ext.window.Window',
    alias : 'widget.transactionform',

    title : 'Edit Transaction',
    layout: 'fit',
    autoShow: true,
    width: 280,
    
    items: [{
        xtype: 'form',
        padding: '5 5 0 5',
        border: false,
        style: 'background-color: #fff;',
        
        fieldDefaults: {
            anchor: '100%',
            labelAlign: 'left',
            allowBlank: false,
            combineErrors: true,
            msgTarget: 'side'
        },

        items: [{
		    xtype: 'textfield',
		    name : 'id',
		    fieldLabel: 'id',
		    hidden:true
		}, {
            xtype: 'textfield',
            name : 'description',
            fieldLabel: 'Description'
        }, {
            xtype: 'datefield',
            name : 'transactionDate',
            fieldLabel: 'Date',
            format: 'd/m/Y',
        }]
    }],
    
    initComponent: function() {
	    this.dockedItems = [{
	        xtype: 'toolbar',
	        dock: 'bottom',
	        id:'buttons',
	        ui: 'footer',
	        items: ['->', {
	            iconCls: 'icon-save',
	            itemId: 'save',
	            text: 'Save',
	            action: 'save'
	        },{
	            iconCls: 'icon-reset',
	            text: 'Cancel',
	            scope: this,
	            handler: this.close
	        }]
	    }];
	    
	    return this.callParent(arguments);
    }
});