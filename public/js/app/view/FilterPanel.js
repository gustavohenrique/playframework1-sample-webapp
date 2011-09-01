Ext.define('PoupaNiquel.view.FilterPanel' ,{
	extend: 'Ext.panel.Panel',
	id: 'filterPanel',
    alias: 'widget.filterPanel',
    title: 'Filter',
    minWidth: 200,
    width: 250,
    split: true,
    border: false,
    frame: false,
    bodyStyle: 'padding-left:5px;padding-top:5px;background:white',
    collapsible: true,
    autoScroll: true,
    xtype: 'form',
    labelWidth: 5,
    defaultType: 'textfield',
    defaults: { anchor: '90%' },
    items: [{
        xtype: 'datefield',
        format: 'd/m/Y',
        name: 'start_date',
        emptyText: 'Start date',
    }, {
        xtype: 'datefield',
        format: 'd/m/Y',
        name: 'end_date',
        emptyText: 'End Date',
    }, Ext.create('Ext.form.field.ComboBox', {
        fieldLabel: '',
        name: 'tipo',
        typeAhead: true,
        triggerAction: 'all',
        allowBlank: true,
        mode: 'local',
        emptyText: 'Tipo de Operação',
        listClass: 'x-combo-list-small',
        valueField: 'id',
        hiddenName: 'tipo',
        displayField: 'display',
        //editable: false,
        store: new Ext.data.SimpleStore({ fields: ['id', 'display'],  data : [ [ "C", "Crédito" ],  [ "D", "Débito" ],  [ "T", "Transferência" ], ] })
    }), Ext.create('Ext.form.field.ComboBox', {
        fieldLabel: '',
        name: 'forma_pagamento',
        typeAhead: true,
        triggerAction: 'all',
        allowBlank: true,
        mode: 'local',
        emptyText: 'Forma de Pagamento',
        listClass: 'x-combo-list-small',
        valueField: 'id',
        hiddenName: 'forma_pagamento',
        displayField: 'display',
        //editable: false,
        store: new Ext.data.SimpleStore({ fields: ['id', 'display'],  data : [ [ 5, "Boleto" ],  [ 6, "Cartão Utilidade" ],  [ 8, "Cheque" ],  [ 2, "Depósito" ],  [ 1, "Dinheiro" ],  [ 7, "Débito em Conta" ],  [ 9, "Mastercard" ],  [ 3, "Transferência" ],  [ 4, "Visa" ], ] })
    }), Ext.create('Ext.form.field.ComboBox', {
        fieldLabel: '',
        name: 'credor',
        typeAhead: true,
        triggerAction: 'all',
        allowBlank: true,
        mode: 'local',
        emptyText: 'Credor',
        listClass: 'x-combo-list-small',
        valueField: 'id',
        hiddenName: 'credor',
        displayField: 'display',
        //editable: false,
        store: new Ext.data.SimpleStore({ fields: ['id', 'display'],  data : [ [ 3, "Casas Bahia" ],  [ 4, "Estácio" ],  [ 1, "Feedback" ],  [ 2, "livrosdeinformatica.com.br" ], ] })
    }), Ext.create('Ext.form.field.ComboBox', {
        fieldLabel: '',
        name: 'caixa',
        typeAhead: true,
        triggerAction: 'all',
        allowBlank: true,
        mode: 'local',
        emptyText: 'Caixa',
        listClass: 'x-combo-list-small',
        valueField: 'id',
        hiddenName: 'caixa',
        displayField: 'display',
        //editable: false,
        store: new Ext.data.SimpleStore({ fields: ['id', 'display'],  data : [ [ 3, "Alimentação" ],  [ 4, "Combustível" ],  [ 2, "Cursos e Palestras" ],  [ 5, "Energia Elétrica" ],  [ 6, "Higiene e Saúde" ],  [ 7, "Impostos" ],  [ 13, "Internet e Informática" ],  [ 12, "Livros e Apostilas" ],  [ 17, "Moradia" ],  [ 14, "Móveis e Utensílios" ],  [ 15, "Outros" ],  [ 11, "Produtos e Acessórios" ],  [ 1, "Salário" ],  [ 8, "Taxas Bancárias" ],  [ 16, "Telefone" ],  [ 9, "Transporte Público" ],  [ 10, "Viagem" ], ] })
    }), Ext.create('Ext.form.field.ComboBox', {
        fieldLabel: '',
        name: 'pago',
        typeAhead: true,
        triggerAction: 'all',
        allowBlank: true,
        mode: 'local',
        emptyText: 'Pago?',
        listClass: 'x-combo-list-small',
        valueField: 'id',
        hiddenName: 'pago',
        displayField: 'display',
        //editable: false,
        store: new Ext.data.SimpleStore({ fields: ['id', 'display'],  data : [ ['S','Sim'], ['N','Não'] ] })
    }), Ext.create('Ext.ux.form.MultiSelect', {
        title: 'tags',
        fieldLabel: '',
        name: 'tags',
        height: 180,
        width: 210,
        allowBlank: true,
        mode: 'local',
        hideOnSelect: false,
        store: [ [ "academia", "academia" ],  [ "acerto", "acerto" ],  [ "aluguel", "aluguel" ],  [ "amigos", "amigos" ],  [ "animal", "animal" ],  [ "besteira", "besteira" ],  [ "buzios", "buzios" ],  [ "cabelo", "cabelo" ],  [ "campos", "campos" ],  [ "cartao", "cartao" ],  [ "celular", "celular" ],  [ "claro", "claro" ],  [ "concurso", "concurso" ],  [ "curso", "curso" ],  [ "dojo", "dojo" ],  [ "estacio", "estacio" ],  [ "evento", "evento" ],  [ "faculdade", "faculdade" ],  [ "familia", "familia" ],  [ "grandmather", "grandmather" ],  [ "hospedagem", "hospedagem" ],  [ "ideais", "ideais" ],  [ "informatica", "informatica" ],  [ "ingles", "ingles" ],  [ "internet", "internet" ],  [ "investimento", "investimento" ],  [ "kinghost", "kinghost" ],  [ "lanche", "lanche" ],  [ "livro", "livro" ],  [ "moto", "moto" ],  [ "namorada", "namorada" ],  [ "natacao", "natacao" ],  [ "natal", "natal" ],  [ "nicole", "nicole" ],  [ "poupanca", "poupanca" ],  [ "praia", "praia" ],  [ "presente", "presente" ],  [ "provedor", "provedor" ],  [ "revista", "revista" ],  [ "roupa", "roupa" ],  [ "salario", "salario" ],  [ "site", "site" ],  [ "thnet", "thnet" ],  [ "unibanco", "unibanco" ],  [ "velox", "velox" ],  [ "viagem", "viagem" ],  [ "visa", "visa" ],  [ "vivi", "vivi" ],  [ "vivio", "vivio" ],  [ "vivo", "vivo" ],  [ "vovo", "vovo" ],  [ "xsol", "xsol" ], ],
        tbar: [{
            text: 'Limpar Seleção',
            iconCls: 'eraser-icon',
            cls:'x-btn-text-icon',
            handler: function() {
                Ext.getCmp('frmFiltro').getForm().findField('tags').reset();
            }
        }]
    })],
    buttons: [{
        text: 'Limpar',
        handler: function() {
            Ext.getCmp('frmFiltro').getForm().reset();
        }
    }, {
        text: 'Filtrar',
        handler: function() {
            var form = Ext.getCmp('frmFiltro').getForm();
            var store = Ext.getCmp('gridLancamento').getStore();
            if (form.isValid())
                store.reload({ params: form.getValues() });
            else
                Ext.Msg.alert('Erro','Preencha corretamente o formulário');
        }
    }],
     
    initComponent: function() {
        return this.callParent(arguments);
    }
});