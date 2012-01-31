var renderMoney = function(value) {
	var formated = Ext.util.Format.currency(Math.abs(value), "R$ ", 2, false);
    if (value > 0)
        return '<span style="color:green;">' + formated + '</span>';
    return '<span style="color:red;">' + formated + '</span>';
};

var renderName = function(value) {
	if (value) return value.name;
}

Ext.define('PoupaNiquel.view.transactions.Grid', {
	extend       : 'PoupaNiquel.view.common.DataGrid',

    columns: [{
    	xtype    : 'rownumberer',
    	width    : 30,
    }, {
        text     : 'Date',
        dataIndex: 'transactionDate',
        sortable : true,
    }, {
    	text     : 'Description',
    	dataIndex: 'description',
    	flex     : 1,
        sortable : false,
        editor: {
            xtype: 'textfield'
        }
    }, {
    	text     : 'Account',
    	dataIndex: 'account',
    	flex     : 0,
        sortable : false,
        renderer : renderName,
        width    : 120,
    }, {
    	text     : 'Category',
    	dataIndex: 'category',
    	flex     : 0,
        sortable : true,
        renderer: renderName,
        width    : 120,
        /*renderer: function(value, meta, record, row, col, store, view) {
        	//if (record.data.category != null) return record.data.category.name;
        	if (store.data.items[row].data.category != null)
        		return store.data.items[row].data.category.name;
        },*/
        editor: {
        	xtype    : 'filterComboBox',
        	store    : 'combobox.Categories',
        	getValue : function() {
        		var name = this.rawToValue(this.processRawValue(this.getRawValue()));
        		return {'id': this.value, 'name': name};
        	},
        }
    }, {
    	text     : 'Payee',
    	dataIndex: 'payee',
    	flex     : 0,
        sortable : false,
        renderer : renderName,
        width    : 120,
    }, {
    	text     : 'Amount',
    	dataIndex: 'amount',
        sortable : false,
        renderer : renderMoney,
    }, {
    	text     : 'Balance',
    	dataIndex: 'balance',
        sortable : false,
        renderer : renderMoney,
    }],
    
});