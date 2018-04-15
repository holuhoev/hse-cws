import React, {Component} from 'react';
import {Menu, Image, Button, Icon} from 'semantic-ui-react';

class NavigationBar extends Component {
    downloadData(workloads, titles) {
        let downloadData, filename, link;
        let csv = this.convertArrayOfObjectsToCSV({
            data: workloads.items,
            titles: titles
        });
        if (csv == null)
            return;
        filename = "data.csv";
        if (!csv.match("data:text\/csv")) {
            csv = 'data:text/csv;charset=utf-8,\ufeff' + csv;
        }
        downloadData = encodeURI(csv);
        link = document.createElement('a');
        link.setAttribute('href', downloadData);
        link.setAttribute('download', filename);
        link.click();
    }

    convertArrayOfObjectsToCSV(args) {
        let result, ctr, keys, columnDelimiter, lineDelimiter, data, titles;
        data = args.data || null;
        titles = args.titles || null;
        if (data == null || !data.length) {
            return null;
        }
        columnDelimiter = ';';
        lineDelimiter = '\n';
        keys = Object.keys(data[0]);
        result = '';
        if (titles && titles.length) {
            result += titles.join(columnDelimiter);
        }
        result += lineDelimiter;
        data.forEach(function (item) {
            ctr = 0;
            keys.forEach(function (key) {
                if (ctr > 0) result += columnDelimiter;
                result += item[key];
                ctr++;
            });
            result += lineDelimiter;
        });
        return result;
    }

    render() {
        const {activeItem, handleItemClick, workloads} = this.props;
        const titles = (activeItem === 'department'
            ? ['Преподаватель', 'Часы']
            : ['Наименование дисциплины', 'Семинары', 'Лекции', 'НИС', 'Практика', 'Показ работ', 'Консультация', 'Экзамен', 'Контрольная работа', 'Другое']);
        return (
            <Menu secondary pointing color='blue'>
                <Menu.Item header>
                    <Image src='https://www.hse.ru/data/2014/06/24/1310196963/logo_с_hse_cmyk.jpg' avatar
                           href='https://www.hse.ru'/>
                </Menu.Item>
                <Menu.Item name='student' primary active={activeItem === 'student'} content='Студенты'
                           onClick={handleItemClick}/>
                <Menu.Item name='lecturer' active={activeItem === 'lecturer'} content='Преподаватели'
                           onClick={handleItemClick}/>
                <Menu.Item name='department' active={activeItem === 'department'} content='Департамент'
                           onClick={handleItemClick}/>
                <Menu.Item position='right'>
                    <Button animated='vertical' primary onClick={() => this.downloadData(workloads, titles)}>
                        <Button.Content visible>Экспорт</Button.Content>
                        <Button.Content hidden>
                            <Icon name='download'/>
                        </Button.Content>
                    </Button>
                </Menu.Item>
            </Menu>)
    }
}


export default NavigationBar;