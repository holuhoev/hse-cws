import React from 'react';
import {Menu, Image, Button, Icon} from 'semantic-ui-react';

const NavigationBar = ({activeItem, handleItemClick, onButtonClick}) => (
    <Menu secondary pointing color='blue'>
        <Menu.Item header>
            <Image src='https://www.hse.ru/data/2014/06/24/1310196963/logo_с_hse_cmyk.jpg' avatar
                   href='https://www.hse.ru'/>
        </Menu.Item>
        <Menu.Item name='students' primary active={activeItem === 'students'} onClick={handleItemClick}/>
        <Menu.Item name='lecturers' active={activeItem === 'lecturers'} onClick={handleItemClick}/>
        <Menu.Item name='department' active={activeItem === 'department'} onClick={handleItemClick}/>
        <Menu.Item position='right'>
            <Button animated='vertical' primary onClick={onButtonClick}>
                <Button.Content visible>Экспорт</Button.Content>
                <Button.Content hidden>
                    <Icon name='download'/>
                </Button.Content>
            </Button>
        </Menu.Item>
    </Menu>
);

export default NavigationBar;