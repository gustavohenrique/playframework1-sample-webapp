Ext.define('PoupaNiquel.view.accounts.Grid', {
	extend: 'Ext.grid.Panel',
    alias: 'widget.accountsGrid',
    
    border: true,
    selModel: {
        selType: 'cellmodel'
    },
    
    columns: [{
    	xtype: 'rownumberer',
    	width: 30,
    }, {
        text: 'Name',
        sortable: true,
        dataIndex: 'name',
        field: {
            xtype: 'textfield'
        }
    }, {
    	text : 'number',
    	flex : 1,
        sortable : false,
        dataIndex: 'number',
        field: {
            xtype: 'textfield'
        }
    }, {
    	text: 'Initital',
    	flex: 0,
        sortable: false,
        dataIndex: 'initital',
        width: 120,
        field: {
            xtype: 'textfield'
        }
    }],
    
    initComponent: function() {
    	this.dockedItems = [{
            xtype: 'toolbar',
            items: [{
                text: 'Add',
                iconCls: 'new-icon',
                action: 'add'
            }, '-', {
                text: 'Delete',
                iconCls: 'delete-icon',
                action: 'delete'
            }]
        }, {
        	xtype: 'pagingtoolbar',
            store: this.getStore(),
            dock: 'bottom',
            beforePageText: '',
            displayMsg: '{0} - {1} of {2}',
            emptyMsg: "Nothing to display",
            displayInfo: true,
        }];
    	
        return this.callParent(arguments);
    },
    
});