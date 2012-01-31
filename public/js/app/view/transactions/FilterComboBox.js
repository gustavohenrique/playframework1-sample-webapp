Ext.define('PoupaNiquel.view.transactions.FilterComboBox' ,{
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.filterComboBox',
	fieldLabel: '',
	store: [],
	valueField: 'id',
	displayField: 'name',
	queryMode: 'local',
	typeAhead: true,
	selectOnFocus: true,
	forceSelection: true,
	allowBlank: true,
	triggerAction: 'all',
	listClass: 'x-combo-list-small',
	minChars: 0,
	
	// bug loadmask in 4.0.7
	createPicker: function() {
        var me = this,
            picker,
            menuCls = Ext.baseCSSPrefix + 'menu',
            opts = Ext.apply({
                pickerField: me,
                selModel: {
                    mode: me.multiSelect ? 'SIMPLE' : 'SINGLE'
                },
                floating: true,
                hidden: true,
                ownerCt: me.ownerCt,
                cls: me.el.up('.' + menuCls) ? menuCls : '',
                store: me.store,
                displayField: me.displayField,
                focusOnToFront: false,
                pageSize: me.pageSize,
                tpl: me.tpl,
                loadMask: me.queryMode === 'local' ? false: true
            }, me.listConfig, me.defaultListConfig);

        picker = me.picker = Ext.create('Ext.view.BoundList', opts);
        if (me.pageSize) {
            picker.pagingToolbar.on('beforechange', me.onPageChange, me);
        }

        me.mon(picker, {
            itemclick: me.onItemClick,
            refresh: me.onListRefresh,
            scope: me
        });

        me.mon(picker.getSelectionModel(), {
            'beforeselect': me.onBeforeSelect,
            'beforedeselect': me.onBeforeDeselect,
            'selectionchange': me.onListSelectionChange,
            scope: me
        });

        return picker;
    },
	
	initComponent: function() {
		return this.callParent(arguments);
	}
});