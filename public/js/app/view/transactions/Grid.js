var onCellRender = function(value) {
	var formated = Ext.util.Format.currency(Math.abs(value), "R$ ", 2, false);
    if (value > 0)
        return '<span style="color:green;">' + formated + '</span>';
    return '<span style="color:red;">' + formated + '</span>';
};

Ext.define('PoupaNiquel.view.transactions.Grid',{
	extend: 'Ext.grid.Panel',
    //extend: 'Ext.ux.LiveSearchGridPanel',
    alias: 'widget.transactionGrid',

    id: 'transactionGrid',
    
    border: true,
    
    columns: [{
    	xtype: 'rownumberer',
    	width: 30,
    }, {
        text     : 'Date',
        sortable : true,
        dataIndex: 'transactionDate',
    }, {
    	text     : 'Description',
    	flex     : 1,
        sortable : false,
        dataIndex: 'description',
        editor: {
            xtype: 'textfield'
        }
    }, {
    	text     : 'Account',
    	flex     : 0,
        sortable : false,
        dataIndex: 'account',
        width    : 120,
        renderer : function(value) {
        	if (value) return value.name;
        }
    }, {
    	text     : 'Category',
    	flex     : 0,
        sortable : true,
        dataIndex: 'category',
        width    : 120,
        renderer : function(value) {
        	if (value) return value.name;
        },
        editor: {
        	xtype: 'filterComboBox',
        	id: 'editCategory',
        	store: 'Categories',
        	listeners: {
        		'afterrender': function () {
	        		var grid = Ext.ComponentManager.get('transactionGrid'),
	        			id = grid.getSelectionModel().getSelection()[0].data.category.id,
	        		    me = this;
	        		
	        		setTimeout(function() { me.setValue(id) }, 500);
        		}
        	},
        	getValue: function() {
        		var name = this.rawToValue(this.processRawValue(this.getRawValue()));
        		var category = {'id': this.value, 'name': name};
                return category;
        	}
        }
    }, {
    	text     : 'Payee',
    	flex     : 0,
        sortable : false,
        dataIndex: 'payee',
        width    : 120,
        renderer : function(value) {
        	if (value) return value.name;
        },
    }, {
    	text     : 'Amount',
        sortable : false,
        dataIndex: 'amount',
        renderer : onCellRender,
    }, {
    	text     : 'Balance',
        sortable : false,
        dataIndex: 'balance',
        renderer : onCellRender,
    }],
    
    tbar: [{
    	text: 'New',
    	iconCls: 'new-icon',
    	action: 'add'
    }, '-', {
        text: 'Edit',
        iconCls: 'edit-icon',
        action: 'edit'
    }, '-', {
        text: 'Delete',
        iconCls: 'delete-icon',
        action: 'delete'
    }],
   
    initComponent: function() {
    	this.dockedItems = [{
            xtype: 'pagingtoolbar',
            store: this.getStore(),
            dock: 'bottom',
            beforePageText: 'Page ',
            displayMsg: '{0} - {1} of {2}',
            emptyMsg: "Nothing to display",
            displayInfo: true,
        }];
        return this.callParent(arguments);
    },
    
});