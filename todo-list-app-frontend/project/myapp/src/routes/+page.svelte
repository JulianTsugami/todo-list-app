<h1>To-Do List</h1>
<script lang="ts">
  import { onMount } from 'svelte';
  import { writable } from 'svelte/store';

  let items = writable([]);

  async function fetchItems() {
    try {
      const response = await fetch('http://localhost:8080/v1/items/');
      const data = await response.json();
      items.set(data);
    } catch (error) {
      console.error('Error fetching items:', error);
    }
  }

  async function handleDeleteClick(id) {
    try {
      const response = await fetch(`http://localhost:8080/v1/items/${id}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
        },
      });
      items.set($items.filter(item => item.id != id))
    } catch (error) {
      console.error('Error occurred:', error);
    }
  }

  onMount(fetchItems);

</script>

<ul>
  {#each $items as item (item.id)}
    <li>{item.description}</li>
    <input type="checkbox" bind:checked={item.isDone}>
    <button on:click={handleDeleteClick(item.id)}>Delete</button>
  {/each}
</ul>
