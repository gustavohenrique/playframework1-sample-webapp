var rowEditing = Ext.create('Ext.grid.plugin.RowEditing');

Ext.define('PoupaNiquel.view.accounts.Grid' ,{
	extend: 'Ext.grid.Panel',
    alias: 'widget.accountsGrid',
    
    store: 'Accounts',
    plugins: [rowEditing],
    border: true,
    
    columns: [{
    	xtype: 'rownumberer',
    	width: 30,
    }, {
        text     : 'Name',
        sortable : true,
        dataIndex: 'name',
        field: {
            xtype: 'textfield'
        }
    }, {
    	text     : 'number',
    	flex     : 1,
        sortable : false,
        dataIndex: 'number',
        field: {
            xtype: 'textfield'
        }
    }, {
    	text     : 'Initital',
    	flex     : 0,
        sortable : false,
        dataIndex: 'initital',
        width    : 120,
        field: {
            xtype: 'textfield'
        }
    }],
    
    initComponent: function() {
    	dockedItems: [{
            xtype: 'toolbar',
            items: [{
                text: 'Add',
                iconCls: 'new-icon',
                handler: function() {
                    store.insert(0, new Person());
                    rowEditing.startEdit(0, 0);
                }
            }, '-', {
                text: 'Delete',
                iconCls: 'delete-icon',
                handler: function() {
                    var selection = self.getView().getSelectionModel().getSelection()[0];
                    if (selection) {
                        store.remove(selection);
                    }
                }
            }]
        }]
        return this.callParent(arguments);
    },
    
});